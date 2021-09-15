import styled from 'styled-components/macro'
import Button from "./Button";

export default function Navigation({ user, ...props }) {
    return (
        <Wrapper {...props}>
            <p>Logged in as {user.username}</p>
            <Button>Log out</Button>
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
    
  }
  
  Button {
    display: block;
    margin: auto;
    padding: 10px;
    border: 1px solid black;
    color: black;
    font-size: 1em;
    border-radius: 15px;
    background: #b4e2ff;
    margin-top: 0%;
  }
  
`