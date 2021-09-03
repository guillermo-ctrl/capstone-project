import { render, screen } from '@testing-library/react';
import App from './App';

test('starts without crashing', () => {
  render(<App />);
});
