import Header from "../components/Header";
import Button from "../components/Button"
import Editor from "../components/Editor";
import { useNavigate } from "react-router-dom"
import { useContext, useEffect } from "react";
import { DispatchStateContext } from "../App";
import usePageTitle from "../hooks/usePageTitle";

const New  = () => {
  const nav = useNavigate();
  const { onCreate } = useContext(DispatchStateContext);
  usePageTitle("새 일기 쓰기");

  const onSubmit = (input) => {
    onCreate(
      input.createdDate.getTime(),
      input.emotionId,
      input.content
    );
    nav('/', {replace: true}); //뒤로가기 방지 옵션, replace
  };

  return ( 
    <div>
      <Header 
        title={"새 일기 쓰기"}
        leftChild={
          <Button 
            onClick={() => nav(-1)}
            text={"< 뒤로가기"} />
        }
        />
      <Editor onSubmit={onSubmit} />
    </div>
  );
};

export default New;