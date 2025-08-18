import { MakeDTO } from '../types/MakeDTO';
import { ModelDTO } from '../types/ModelDTO';

export type Car = {
  id: string;
  make: string;
  model: string;
  doors?: number;
  bodyType?: string;
  transmission?: string;
  fuelType?: string;
  [k: string]: unknown;
};

export type CarFilters = Partial<{
  make: string;
  model: string;
  doors: string | number;
  bodyType: string;
  transmission: string;
  fuelType: string;
}>;

const BASE_URL = import.meta.env.VITE_BACKEND_BASE_URL ?? 'http://localhost:8080';
const BASE_PATH = import.meta.env.VITE_BACKEND_BASE_PATH ?? '/api/cars';

export function buildSearchParams(filters: CarFilters): string {
  const params = new URLSearchParams();
  Object.entries(filters).forEach(([key, val]) => {
    if (val === undefined || val === null || val === '' || val === 'any') return;
    params.set(key, String(val));
  });
  const q = params.toString();
  return q ? `?${q}` : '';
}

export async function fetchCars(filters: CarFilters = {}, signal?: AbortSignal) {
  const query = buildSearchParams(filters);
  const url = `${BASE_URL}${BASE_PATH}${query}`;
  const res = await fetch(url, { signal: signal ?? null, headers: { Accept: 'application/json' } });
  if (!res.ok) throw new Error(`Request failed: ${res.status}`);
  return res.json();
}

export async function fetchMakes(signal?: AbortSignal): Promise<MakeDTO[]> {
  const url = `${BASE_URL}${BASE_PATH}/makes`;
  const res = await fetch(url, { signal: signal ?? null, headers: { Accept: 'application/json' } });
  if (!res.ok) throw new Error(`Request failed: ${res.status}`);
  return res.json();
}

export async function fetchModelsByMake(makeId: number, signal?: AbortSignal): Promise<ModelDTO[]> {
  const url = `${BASE_URL}${BASE_PATH}/models?makeId=${makeId}`;
  const res = await fetch(url, { signal: signal ?? null, headers: { Accept: 'application/json' } });
  if (!res.ok) throw new Error(`Request failed: ${res.status}`);
  return res.json();
}
