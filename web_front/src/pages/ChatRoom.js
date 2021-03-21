import { Col, Button, Row } from "antd";
import { useEffect, useRef, useState } from "react";
import ChatList from "../components/ChatList";
import ChatWindow from "../components/ChatWindow";
import ChatLogIn from "../components/ChatLogIn";

export default function ChatRoom() {
    const [hasLogIn, setLogIn] = useState(false);
    const [userList, setUserList] = useState([]);
    const [dialogList, setDialogList] = useState([]);
    const [chatName, setChatName] = useState('main');
    const [buf, setBuf] = useState({});
    const wsocket = useRef(null);

    const onMessage = (evt) => {
        let data = JSON.parse(evt.data);
        if (data.type === "hello") {
            console.log("receive hello");
        } else if (data.type === "userList") {
            setUserList(data.userList);
        } else if (data.type === "chatList") {
            setDialogList(data.chatList);
        }
        else if (data.type === "chat") {
            setBuf(data);
        }
    }

    useEffect(() => {
        wsocket.current = new WebSocket("ws://localhost:8080/ebook");
        wsocket.current.onopen = () => { console.log("ws open") };
        wsocket.current.onclose = () => { alert("web socket close"); console.log("ws close") };
        wsocket.current.onmessage = (evt) => { onMessage(evt) };
        return () => {
            wsocket.current.close();
        }
    }, []);

    useEffect(() => {
        if (wsocket.current.readyState === wsocket.current.OPEN) {
            let fetchMsg = {}
            fetchMsg.type = "fetch";
            fetchMsg.chatName = chatName;
            wsocket.current.send(JSON.stringify(fetchMsg));
        }
    }, [chatName]);

    useEffect(() => {
        
        console.log(chatName)
        if (buf.chatName !== chatName) {
            console.log("drop!!!");
            return;
        }
        setDialogList(dialogList => [...dialogList, { userName: buf.userName, content: buf.content, time: buf.time }])
    }, [buf]);

    return (
        <>
            <Button onClick={() => console.log(chatName)}>test</Button>
            {hasLogIn ?
                <Row gutter={16}>
                    <Col span={6}>
                        <ChatList setChatName={setChatName} userList={userList} chatName={chatName} />
                    </Col>
                    <Col span={18}>
                        <ChatWindow wsocket={wsocket} chatName={chatName} chatList={dialogList} setLogIn={setLogIn} />
                    </Col>
                </Row> :
                <ChatLogIn wsocket={wsocket} setLogIn={setLogIn} />
            }
        </>
    )
}
