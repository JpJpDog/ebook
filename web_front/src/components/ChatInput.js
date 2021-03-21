import { Input, Button } from 'antd';
import { useState } from 'react';

const { TextArea } = Input;

export default function ChatInput({ wsocket, chatName }) {
    const [inputText, setText] = useState("");

    const sendChat = () => {
        let chatMsg = {};
        chatMsg.type = "chat";
        chatMsg.content = inputText;
        chatMsg.userName = localStorage.getItem("userName");
        chatMsg.chatName = chatName;
        wsocket.current.send(JSON.stringify(chatMsg));
        console.log(chatName)
        setText("");
    }

    return (<>
        <TextArea value={inputText} onChange={e => setText(e.target.value)} />
        <div><Button onClick={sendChat}>Submit</Button></div>
    </>);
}