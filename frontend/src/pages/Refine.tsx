import React, { FormEvent, useMemo } from 'react';
import { useNavigate } from 'react-router-dom';
import Dropdown from '../components/Dropdown/Dropdown';
import { useRefineForm, RefineFormState } from '../hooks/useRefineForm';

const ANY = { label: "I don't mind", value: 'any' };

const RAW_DOORS = [ANY, '2', '3', '4', '5'];

const RAW_BODY_TYPES = [
  ANY,
  'Cargo Van',
  'Convertible',
  'Coupe',
  'Ext Van',
  'Hatchback',
  'Minivan',
  'Passenger Van',
  'Sedan',
  'SUV',
  'Truck',
  'Van',
  'Wagon',
];

const RAW_TRANSMISSIONS = [ANY, 'Manual', 'Automatic'];
const RAW_FUEL_TYPES = [ANY, 'Petrol', 'Diesel', 'Hybrid', 'Electric', 'Hydrogen'];

export default function Refine() {
  const navigate = useNavigate();
  const [form, updateForm] = useRefineForm();

  const DOORS = useMemo(
    () => RAW_DOORS.map((d) => (typeof d === 'string' ? { label: d, value: d } : d)),
    [],
  );

  const BODY_TYPES = useMemo(
    () => RAW_BODY_TYPES.map((b) => (typeof b === 'string' ? { label: b, value: b } : b)),
    [],
  );

  const TRANSMISSIONS = useMemo(
    () => RAW_TRANSMISSIONS.map((t) => (typeof t === 'string' ? { label: t, value: t } : t)),
    [],
  );

  const FUEL_TYPES = useMemo(
    () => RAW_FUEL_TYPES.map((f) => (typeof f === 'string' ? { label: f, value: f } : f)),
    [],
  );

  const onSubmit = (e: FormEvent) => {
    e.preventDefault();
    const qs = new URLSearchParams();
    Object.entries(form).forEach(([key, value]) => {
      if (value && value !== 'any') qs.set(key, value);
    });
    navigate({ pathname: '/results', search: qs.toString() });
  };

  const renderDropdown = (label: string, options: typeof DOORS, key: keyof RefineFormState) => (
    <Dropdown
      label={label}
      options={options}
      value={form[key]}
      onChange={(e) => updateForm(key, e.target.value)}
    />
  );

  return (
    <section>
      <h2>Refine your search</h2>
      <form onSubmit={onSubmit}>
        {renderDropdown('Number of doors', DOORS, 'doors')}
        {renderDropdown('Body type', BODY_TYPES, 'bodyType')}
        {renderDropdown('Transmission', TRANSMISSIONS, 'transmission')}
        {renderDropdown('Fuel type', FUEL_TYPES, 'fuelType')}
        <button type="submit" aria-label="get results">
          Get results
        </button>
      </form>
    </section>
  );
}
