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

width: 80%;
padding-top: 5px;
padding-bottom: 5px;
border-radius: 10px;
background: #b4e2ff;
margin: 1em;
font-size: 25px;
display: block;
vertical-align: middle;
  
  
  a {
    text-decoration: none;
  }
`
