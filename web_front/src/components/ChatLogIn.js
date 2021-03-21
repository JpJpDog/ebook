import { Input, Button, Row } from 'antd';
import { useState, useEffect } from 'react';

export default function ChatLogIn({ setLogIn, wsocket }) {
    const [userName, setUserName] = useState("");
    useEffect(() => {
        setUserName(localStorage.getItem("userName") || "");
    }, []);

    const JoinIn = () => {
        localStorage.setItem("userName", userName);
        if (userName === "") {
            alert("empty username is not allowed!");
            return;
        }
        localStorage.setItem("userName", userName);
        setLogIn(true);
        let joinMsg = {};
        joinMsg.type = "join";
        joinMsg.userName = localStorage.getItem("userName");
        wsocket.current.send(JSON.stringify(joinMsg));
    }

    return (<div>
        <div>Your user name is <Input value={userName} onChange={e => setUserName(e.target.value)} /></div>
        <Button onClick={() => { localStorage.removeItem("userName"); setUserName("") }}>Quit!</Button>
        <Button onClick={JoinIn}>Join in!</Button>
    </div>)
}