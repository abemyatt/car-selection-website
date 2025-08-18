import { useState } from 'react';
import { useSearchParams } from 'react-router-dom';

export interface RefineFormState {
  make: string;
  model: string;
  doors: string;
  bodyType: string;
  transmission: string;
  fuelType: string;
}

export function useRefineForm(): [
  RefineFormState,
  (key: keyof RefineFormState, value: string) => void,
] {
  const [params] = useSearchParams();

  const [form, setForm] = useState<RefineFormState>({
    make: params.get('make') ?? '',
    model: params.get('model') ?? '',
    doors: params.get('doors') ?? 'any',
    bodyType: params.get('bodyType') ?? 'any',
    transmission: params.get('transmission') ?? 'any',
    fuelType: params.get('fuelType') ?? 'any',
  });

  const updateForm = (key: keyof RefineFormState, value: string) => {
    setForm((prev) => ({ ...prev, [key]: value }));
  };

  return [form, updateForm];
}
