import styled from 'styled-components/macro'
import {useHistory} from "react-router-dom";

export default function SingleDocument({...props}) {
    const history = useHistory();

    const handleClick = () => {
        history.push(`/details/${props.document.imageId}`)
    }

    return (
        <Wrapper>
            {props.document && <img src={props.document.url} alt="" onClick={handleClick}/>}
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