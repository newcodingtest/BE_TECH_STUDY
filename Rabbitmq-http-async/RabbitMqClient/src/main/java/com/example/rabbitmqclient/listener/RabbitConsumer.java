package com.example.rabbitmqclient.listener;

import com.example.rabbitmqclient.service.HttpService;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
@Component
public class RabbitConsumer {

    @Value("url1")
    private String url1;
    @Value("url2")
    private String url2;

    private final HttpService httpService;

    @RabbitListener(queues = "q.esi.fdp.saveFpl.backup")
    public void getQ1(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag){
        processMsgByAsync(message, url1, channel, deliveryTag);
    }

    @RabbitListener(queues = "q.esi.sdp.radar.raw")
    public void getQ2(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag){
        processMsgByAsync(message, url2, channel, deliveryTag);
    }

    @Async
    public void processMsgByAsync(String msg, String url, Channel channel, long deliveryTag){
            log.info(Thread.currentThread().getName());

            httpService.apiPostCallAsync(msg,url)
                    .subscribe(res -> {
                                int responseCode = res.value();
                                log.info("response code ::: {} ", responseCode);

                                if (responseCode!=200){
                                    try {
                                        channel.basicAck(deliveryTag, false);
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            },
                            error -> {
                                log.error(error.getMessage());
                                try {
                                    channel.basicAck(deliveryTag, false);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    );

        }

}
