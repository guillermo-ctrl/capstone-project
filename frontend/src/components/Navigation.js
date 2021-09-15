import styled from 'styled-components/macro'

export default function Navigation({ user, ...props }) {
    return (
        <Wrapper {...props}>
            <p>Logged in as {user.username}</p>
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
  padding-right: 5%;
  }
  
`