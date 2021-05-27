import { useEffect } from "react";

export default function ChatMsg({ msg }) {
    let content;
    if (msg.userName === "system") {
        content = (<div style={{ color: "grey" }}>system: {msg.content}</div>);
    } else if (msg.username !== localStorage.getItem("userName")) {
        content = (<div>{msg.userName}: {msg.content}</div>);
    } else {
        content = (<div>
            <span style={{ color: 'blue' }}>{msg.userName}</span>:
            {msg.value}</div>);
    }
    return <div style={{ width: '100%' }}>
        {content}
        <div style={{ textAlign: 'right', width: '100%' }}>{msg.time}</div>
    </div>
}