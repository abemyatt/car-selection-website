import { useState, useEffect } from 'react';
import { fetchCars } from '../services/api';
import { CarDTO } from '../types/CarDTO';

export interface Car {
  make: string;
  model: string;
  bodyType?: string | undefined;
  doors?: number | undefined;
  transmission?: string | undefined;
  fuelType?: string | undefined;
  engineSize?: number | undefined;
  trimName?: string | undefined;
}

export function useCars(filters: Record<string, string>) {
  const [cars, setCars] = useState<Car[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const controller = new AbortController();
    setLoading(true);
    setError(null);

    fetchCars(filters, controller.signal)
      .then((data: CarDTO[]) => {
        const mapped: Car[] = data.map((c) => ({
          make: c.makeName,
          model: c.modelName,
          bodyType: c.bodyType,
          doors: c.bodyDoors,
          transmission: c.engineTransmission,
          fuelType: c.engineFuelType,
          engineSize: c.engineSize,
          trimName: c.trimName,
        }));
        setCars(mapped);
      })
      .catch((err: unknown) => {
        if ((err as { name?: string }).name !== 'AbortError') {
          setError((err as Error).message || String(err));
        }
      })
      .finally(() => setLoading(false));

    return () => controller.abort();
  }, [filters]);

  return { cars, loading, error };
}
