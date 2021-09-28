import styled from "styled-components/macro";
import {Link} from "react-router-dom";

export default function MainPageButton({...props}) {
    return(

        <BigButton>
            <Link to = {props.url} >{props.linkText}</Link>
        </BigButton>
    )
}

const BigButton = styled.button`

    width: 95%;
    padding: 30px 10px;
    background: transparent;
    margin: 1em auto;
    font-size: 29px;
    display: block;
    vertical-align: middle;
    border: 2px white solid;
  
  a {
    text-decoration: none;
    color: white;
  }
`
