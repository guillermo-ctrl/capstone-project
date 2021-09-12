import {useAuth} from "../auth/AuthProvider";

export default function Browse() {
    const {user} = useAuth()

    return (
        <div>

        <h1>browse stuff</h1>
        <p>{user.username}</p>

        </div>

    )
}