import styled from "styled-components/macro";

export default function TextField({
    title,
    ...props
}) {
    return(
        <Wrapper>
            {title}
            <input{...props} title={title}/>
        </Wrapper>

    )
}

const Wrapper = styled.div`
    input{
        width: 100%;
        font-size: 1em;
        padding-top: 5px;
        padding-bottom: 5px;
      margin-top: 10px;
      border-radius: var(--size-s);
    }
  margin-bottom: 20px;
 
`
