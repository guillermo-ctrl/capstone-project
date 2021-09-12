import styled from 'styled-components/macro'

export default function Loading(props) {
  return (
    <Wrapper {...props}>
        <p>Loading</p>
    </Wrapper>
  )
}

const Wrapper = styled.div`
  display: inline-block;
  position: relative;
  width: 80px;
  height: 80px;
`
