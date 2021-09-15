import styled from 'styled-components/macro'
import {Redirect, useHistory} from "react-router-dom";

export default function SingleDocument({...props}) {
    const history = useHistory();

    const handleClick = () => {
        console.log(props.document)
        history.push(`/details/${props.document.imageId}`)
    }

    return (
        <Wrapper>
            <img src={props.document.url} alt="" onClick={handleClick}/>
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