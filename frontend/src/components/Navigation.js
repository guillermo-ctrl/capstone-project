import styled from 'styled-components/macro'
import {useAuth} from "../auth/AuthProvider";
import LogoutButton from "./LogoutButton";
import {Redirect} from "react-router-dom";

export default function Navigation({...props }) {
    const { user, logout } = useAuth()
    if (!user) return <Redirect to = "/login"></Redirect>
    return (
        <Wrapper {...props}>
            {user && <p>Logged in as {user.username}</p>}
            <LogoutButton onClick={logout}>Log out</LogoutButton>
        </Wrapper>
    )
}

const Wrapper = styled.nav`
  border-bottom: 1px solid black;
  width: 100%;
  display: flex;         
  overflow-y: scroll;
  background: #15aabf;
  justify-content: right;
  height: 60px;
  align-items: center;

  
  p {
  font-size: 1em;
  color: white;
 
  }
  
`