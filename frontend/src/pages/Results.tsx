import React, { useMemo } from 'react';
import { useSearchParams } from 'react-router-dom';
import { useCars } from '../hooks/useCars';
import { CarList } from '../components/Cars/CarList';
import { Loading } from '../components/Cars/Loading';
import { ErrorMessage } from '../components/Cars/ErrorMessage';

export default function Results() {
  const [searchParams] = useSearchParams();

  const filters = useMemo(() => {
    const obj: Record<string, string> = {};
    searchParams.forEach((value, key) => {
      obj[key] = value;
    });
    return obj;
  }, [searchParams]);

  const { cars, loading, error } = useCars(filters);

  if (loading) return <Loading />;
  if (error) return <ErrorMessage message={error} />;
  if (cars.length === 0) return <p>No cars found.</p>;

  return (
    <section>
      <h1 aria-label="we recommend">We recommend any of the following cars</h1>
      <p aria-live="polite" style={{ fontStyle: 'italic' }}>
        Endpoint:{' '}
        <code>
          /cars{Object.keys(filters).length ? '?' + new URLSearchParams(filters).toString() : ''}
        </code>
      </p>

      <CarList cars={cars} />
    </section>
  );
}
