import { renderHook, waitFor } from '@testing-library/react';
import { Mock, describe, it, expect, vi, beforeEach } from 'vitest';
import { useMakes } from '../hooks/useMakes';
import { fetchMakes } from '../services/api';
import type { MakeDTO } from '../types/MakeDTO';

vi.mock('../services/api', () => ({
  fetchMakes: vi.fn(),
}));

describe('useMakes', () => {
  beforeEach(() => {
    vi.clearAllMocks();
  });

  it('returns makes when fetchMakes resolves', async () => {
    const mockMakes: MakeDTO[] = [
      { makeId: 1, makeName: 'Toyota' },
      { makeId: 2, makeName: 'Honda' },
    ];

    (fetchMakes as unknown as Mock).mockResolvedValueOnce(mockMakes);

    const { result } = renderHook(() => useMakes());

    expect(result.current).toEqual([]);

    await waitFor(() => {
      expect(result.current).toEqual(mockMakes);
    });
  });

  it('logs error when fetchMakes rejects', async () => {
    const error = new Error('fetch failed');
    (fetchMakes as unknown as Mock).mockRejectedValueOnce(error);

    const consoleSpy = vi.spyOn(console, 'error').mockImplementation(() => {});

    const { result } = renderHook(() => useMakes());

    expect(result.current).toEqual([]);

    await waitFor(() => {
      expect(consoleSpy).toHaveBeenCalledWith(error);
    });

    expect(result.current).toEqual([]);

    consoleSpy.mockRestore();
  });
});
