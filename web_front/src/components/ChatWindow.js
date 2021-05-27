import { useEffect, useState } from "react";

import { List, Row, Button } from 'antd';
import ChatMsg from "./ChatMsg";
import ChatInput from "./ChatInput";

export default function ChatWindow({ wsocket, setLogIn, chatList, chatName }) {
    const quitChat = () => {
        let quitMsg = {};
        quitMsg.type = "quit";
        quitMsg.userName = localStorage.getItem("userName");
        wsocket.current.send(JSON.stringify(quitMsg));
        setLogIn(false);
    }

    return (<>
        <List
            dataSource={chatList}
            renderItem={item => <Row><ChatMsg msg={item} /></Row>}
        />
        <ChatInput wsocket={wsocket} chatName={chatName} />
        <Button onClick={quitChat}>Quit</Button>
    </>);
}