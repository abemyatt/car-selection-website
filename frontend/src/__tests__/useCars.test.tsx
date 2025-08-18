import { renderHook, waitFor } from '@testing-library/react';
import { Mock, describe, it, expect, vi, beforeEach } from 'vitest';
import { useCars } from '../hooks/useCars';
import { fetchCars } from '../services/api';
import type { CarDTO } from '../types/CarDTO';

vi.mock('../services/api', () => ({
  fetchCars: vi.fn(),
}));

describe('useCars', () => {
  const mockFilters = { make: 'Toyota', model: 'Corolla' };

  beforeEach(() => {
    vi.clearAllMocks();
  });

  it('returns cars when fetchCars resolves', async () => {
    const mockResponse: CarDTO[] = [
      {
        makeName: 'Toyota',
        modelName: 'Corolla',
        bodyType: 'Sedan',
        bodyDoors: 4,
        engineTransmission: 'Automatic',
        engineFuelType: 'Petrol',
        engineSize: 1800,
        trimName: 'LE',
      },
    ];

    (fetchCars as unknown as Mock).mockResolvedValueOnce(mockResponse);

    const { result } = renderHook(() => useCars(mockFilters));

    expect(result.current.loading).toBe(true);
    expect(result.current.cars).toEqual([]);
    expect(result.current.error).toBeNull();

    await waitFor(() => {
      expect(result.current.loading).toBe(false);
    });

    expect(result.current.error).toBeNull();
    expect(result.current.cars).toEqual([
      {
        make: 'Toyota',
        model: 'Corolla',
        bodyType: 'Sedan',
        doors: 4,
        transmission: 'Automatic',
        fuelType: 'Petrol',
        engineSize: 1800,
        trimName: 'LE',
      },
    ]);
  });

  it('sets error when fetchCars rejects', async () => {
    (fetchCars as unknown as Mock).mockRejectedValueOnce(new Error('fetch failed'));

    const { result } = renderHook(() => useCars(mockFilters));

    expect(result.current.loading).toBe(true);

    await waitFor(() => {
      expect(result.current.loading).toBe(false);
    });

    expect(result.current.cars).toEqual([]);
    expect(result.current.error).toBe('fetch failed');
  });

  it('ignores AbortError rejections', async () => {
    (fetchCars as unknown as Mock).mockRejectedValueOnce({ name: 'AbortError' });

    const { result } = renderHook(() => useCars(mockFilters));

    await waitFor(() => {
      expect(result.current.loading).toBe(false);
    });

    expect(result.current.error).toBeNull();
    expect(result.current.cars).toEqual([]);
  });
});
