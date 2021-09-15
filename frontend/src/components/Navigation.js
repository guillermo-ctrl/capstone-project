import styled from 'styled-components/macro'
import LoginButton from "./LoginButton";
import {useAuth} from "../auth/AuthProvider";
import LogoutButton from "./LogoutButton";

export default function Navigation({...props }) {
    const { user, logout } = useAuth()
    return (
        <Wrapper {...props}>
            <p>Logged in as {user.username}</p>
            <LogoutButton onClick={logout}>Log out</LogoutButton>
        </Wrapper>
    )
}

const Wrapper = styled.nav`
  border-bottom: 1px solid black;
  width: 100%;
  display: flex;         
  overflow-y: scroll;
  background: #b4e2ff;
  justify-content: right;
  
  p {
  font-size: 1em;
  }
  
`