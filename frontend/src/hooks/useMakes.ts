import { useEffect, useState } from 'react';
import { fetchMakes } from '../services/api';
import { MakeDTO } from '../types/MakeDTO';

export function useMakes() {
  const [makes, setMakes] = useState<MakeDTO[]>([]);

  useEffect(() => {
    fetchMakes().then(setMakes).catch(console.error);
  }, []);

  return makes;
}
