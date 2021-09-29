import styled from 'styled-components/macro'
import background_main from "../images/background_main.jpeg";

const thing = function() {
    return(
        <img src = {background_main}/>
    )
}
export default styled.div`

position: absolute;
top:0;
left:0;
width: 100%;
background: white;
color: gray;
overflow-y: scroll;
display: grid;
place-items: center;
grid-template-rows: min-content 1fr min-content;
background-image: url(${background_main});
height: 100%;
background-position: center;
background-repeat: no-repeat;
background-size: cover;

`