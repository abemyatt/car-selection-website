import React, { SelectHTMLAttributes } from 'react';
import './Dropdown.css';

type Props = SelectHTMLAttributes<HTMLSelectElement> & {
  label: string;
  options: { label: string; value: string }[];
};

export default function Dropdown({ label, options, id, ...rest }: Props) {
  const selectId = id ?? label.toLowerCase().replace(/\s+/g, '-');
  return (
    <div className="dropdown-wrapper">
      <label htmlFor={selectId} className="dropdown-label">
        {label}
      </label>
      <select id={selectId} {...rest} className="dropdown-select">
        {options.map((opt) => (
          <option key={opt.value} value={opt.value}>
            {opt.label}
          </option>
        ))}
      </select>
    </div>
  );
}
