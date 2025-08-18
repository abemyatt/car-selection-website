import React from 'react';
import { CarItem } from './CarItem';
import { Car } from '../../hooks/useCars';

interface Props {
  cars: Car[];
}

export const CarList: React.FC<Props> = ({ cars }) => (
  <ul style={{ display: 'grid', gap: '0.75rem', listStyle: 'none', padding: 0 }}>
    {cars.map((car, idx) => (
      <CarItem key={idx} car={car} />
    ))}
  </ul>
);
