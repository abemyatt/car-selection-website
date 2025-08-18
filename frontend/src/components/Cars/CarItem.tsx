import React from 'react';
import { Car } from '../../hooks/useCars';

interface Props {
  car: Car;
}

export const CarItem: React.FC<Props> = ({ car }) => (
  <li
    style={{
      border: '1px solid #ddd',
      borderRadius: 8,
      padding: '0.75rem',
    }}
  >
    <strong>
      {car.make} {car.model}
    </strong>
    <div
      style={{
        fontSize: '.9rem',
        marginTop: 4,
        display: 'flex',
        flexWrap: 'wrap',
        gap: '0.5rem 1rem',
      }}
    >
      {car.bodyType && <span>Body: {car.bodyType}</span>}
      {car.doors != null && <span>Doors: {car.doors}</span>}
      {car.transmission && <span>Transmission: {car.transmission}</span>}
      {car.fuelType && <span>Fuel: {car.fuelType}</span>}
      {car.engineSize != null && <span>Engine: {car.engineSize}L</span>}
      {car.trimName && <span>Trim: {car.trimName}</span>}
    </div>
  </li>
);
