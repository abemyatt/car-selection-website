import { renderHook, waitFor } from '@testing-library/react';
import { Mock, describe, it, expect, vi, beforeEach } from 'vitest';
import { useModels } from '../hooks/useModels';
import { fetchModelsByMake } from '../services/api';
import type { ModelDTO } from '../types/ModelDTO';

vi.mock('../services/api', () => ({
  fetchModelsByMake: vi.fn(),
}));

describe('useModels', () => {
  beforeEach(() => {
    vi.clearAllMocks();
  });

  it('returns empty array if makeId is null', () => {
    const { result } = renderHook(() => useModels(null));
    expect(result.current).toEqual([]);
    expect(fetchModelsByMake).not.toHaveBeenCalled();
  });

  it('fetches and sets models for a valid makeId', async () => {
    const mockModels: ModelDTO[] = [
      { modelId: 1, modelName: 'Corolla' },
      { modelId: 2, modelName: 'Yaris' },
    ];
    (fetchModelsByMake as unknown as Mock).mockResolvedValueOnce(mockModels);

    const { result } = renderHook(() => useModels(1));

    expect(result.current).toEqual([]);

    await waitFor(() => {
      expect(fetchModelsByMake).toHaveBeenCalledWith(1);
      expect(result.current).toEqual(mockModels);
    });
  });
});
