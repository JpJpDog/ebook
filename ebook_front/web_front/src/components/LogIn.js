import { Button, Input } from "antd"
import { useState } from "react"
import { postFetch } from '../utils/fetchData';

const logInURL = "http://localhost:8080/user/logIn";

export default function LogIn() {
    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");

    const handleLogIn = (rsp) => {
        if (parseInt(rsp) === 0) {
            alert("Log In OK!");
        } else {
            alert("Log In Fail!");
        }
    }

    const postLogIn = () => {
        postFetch(logInURL, {
            userName: userName,
            password, password
        }, handleLogIn)
    }


    return <>
        <Input placeholder="user name" value={userName} onChange={e => setUserName(e.target.value)} />
        <Input placeholder="password" value={password} onChange={e => setPassword(e.target.value)} />
        <Button onClick={postLogIn}>Log In!</Button>
    </>
}