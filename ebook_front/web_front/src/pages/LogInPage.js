import { useEffect, useRef, useState } from "react"
import LogIn from '../components/LogIn';

export default function LogInPage() {
    const wsocket = useRef(null);
    const [visitN, setVisitN] = useState(0);
    const onMessage = (evt) => {
        let data = JSON.parse(evt.data);
        console.log(data);
        if (data.type === "visitN") {
            setVisitN(parseInt(data.visitN));
        }
    }
    const onOpen = () => {
        console.log("ws show open");
        let msg = {};
        msg.type = "getVisitN";
        wsocket.current.send(JSON.stringify(msg));
    }
    useEffect(() => {
        wsocket.current = new WebSocket("ws://localhost:8080/statistic");
        wsocket.current.onopen = onOpen;
        wsocket.current.onmessage = onMessage;
        return () => {
            wsocket.current.close();
        }
    }, []);

    return <>
        <div>visit number: {visitN}</div>
        <LogIn />
    </>
}