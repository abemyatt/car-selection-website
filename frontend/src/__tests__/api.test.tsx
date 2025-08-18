import { describe, it, beforeEach, afterEach, expect, vi, Mock } from 'vitest';
import * as api from '../services/api';
import { MakeDTO } from '../types/MakeDTO';
import { ModelDTO } from '../types/ModelDTO';

describe('api.ts', () => {
  let fetchMock: Mock;

  beforeEach(() => {
    fetchMock = vi.fn();
    (global.fetch as unknown) = fetchMock;
  });

  afterEach(() => {
    vi.restoreAllMocks();
  });

  describe('buildSearchParams', () => {
    it('should return empty string when filters are empty', () => {
      expect(api.buildSearchParams({})).toBe('');
    });

    it('should ignore undefined, null, empty, or "any" values', () => {
      const params = api.buildSearchParams({
        make: 'Toyota',
        fuelType: 'any',
      });
      expect(params).toBe('?make=Toyota');
    });

    it('should convert all values to strings', () => {
      const params = api.buildSearchParams({
        doors: 4,
        transmission: 'Manual',
      });
      expect(params).toBe('?doors=4&transmission=Manual');
    });
  });

  describe('fetchCars', () => {
    it('calls fetch with correct URL and returns JSON', async () => {
      const mockResponse = [{ id: '1', make: 'Toyota', model: 'Corolla' }];
      fetchMock.mockResolvedValue({
        ok: true,
        json: () => Promise.resolve(mockResponse),
      } as unknown as Response);

      const filters = { make: 'Toyota' };
      const result = await api.fetchCars(filters);

      expect(fetchMock).toHaveBeenCalledWith(
        expect.stringContaining('Toyota'),
        expect.objectContaining({ headers: { Accept: 'application/json' } }),
      );
      expect(result).toEqual(mockResponse);
    });

    it('throws an error if fetch fails', async () => {
      fetchMock.mockResolvedValue({ ok: false, status: 500 } as unknown as Response);

      await expect(api.fetchCars()).rejects.toThrow('Request failed: 500');
    });
  });

  describe('fetchMakes', () => {
    it('fetches makes and returns JSON', async () => {
      const makes: MakeDTO[] = [{ makeId: 1, makeName: 'Toyota' }];
      fetchMock.mockResolvedValue({
        ok: true,
        json: () => Promise.resolve(makes),
      } as unknown as Response);

      const result = await api.fetchMakes();
      expect(result).toEqual(makes);
    });

    it('throws an error if fetch fails', async () => {
      fetchMock.mockResolvedValue({ ok: false, status: 404 } as unknown as Response);
      await expect(api.fetchMakes()).rejects.toThrow('Request failed: 404');
    });
  });

  describe('fetchModelsByMake', () => {
    it('fetches models for a given makeId', async () => {
      const models: ModelDTO[] = [{ modelId: 1, modelName: 'Corolla' }];
      fetchMock.mockResolvedValue({
        ok: true,
        json: () => Promise.resolve(models),
      } as unknown as Response);

      const result = await api.fetchModelsByMake(1);
      expect(fetchMock).toHaveBeenCalledWith(
        expect.stringContaining('makeId=1'),
        expect.objectContaining({ headers: { Accept: 'application/json' } }),
      );
      expect(result).toEqual(models);
    });

    it('throws an error if fetch fails', async () => {
      fetchMock.mockResolvedValue({ ok: false, status: 500 } as unknown as Response);
      await expect(api.fetchModelsByMake(1)).rejects.toThrow('Request failed: 500');
    });
  });
});
