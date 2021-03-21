import { Divider, List } from "antd";

export default function ChatList({ userList, setChatName, chatName }) {
    return (
        <div>
            <div>your name: {localStorage.getItem("userName")}</div>
            <Divider />
            {chatName === "main" ?
                <div style={{ backgroundColor: "yellow", width: "100%" }}>Chat Room</div> :
                <a onClick={() => setChatName("main")}>Chat Room</a>
            }
            <Divider />
            <List
                dataSource={userList}
                renderItem={item => (
                    item.userName === localStorage.getItem("userName") ?
                        <List.Item actions={[<span>me</span>]}>
                            <List.Item.Meta title={item.userName} />
                        </List.Item>
                        : <List.Item actions={[item.active === true ?
                            <a onClick={() => setChatName(item.userName)}>chat</a> :
                            <span>not active</span>]} style={{ backgroundColor: chatName === item.userName && "yellow" }}>
                            <List.Item.Meta title={item.userName} />
                        </List.Item>
                )}
            />
        </div>
    )
}
