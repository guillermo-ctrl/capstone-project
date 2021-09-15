import styled from 'styled-components/macro'

export default function SingleDocument({...props}) {
    return (
        <Wrapper>
            <img src={props.document.url} alt=""/>
        </Wrapper>

    )
}

const Wrapper = styled.div`
  padding: 24px;
  width: 300px;
  text-align: center;
  border: 1px solid #333;
  border-radius: 12px;
  box-shadow: 1px 2px 8px #666;
  
  img {
    width: 100%;
  }
`