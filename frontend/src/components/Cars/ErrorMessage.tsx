import React from 'react';

interface Props {
  message: string;
}

export const ErrorMessage: React.FC<Props> = ({ message }) => (
  <div role="alert" style={{ color: 'crimson' }}>
    <p>Something went wrong while fetching cars.</p>
    <pre style={{ whiteSpace: 'pre-wrap' }}>{message}</pre>
  </div>
);
