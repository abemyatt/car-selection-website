import React from 'react';
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { MemoryRouter, Route, Routes } from 'react-router-dom';
import Home from '../pages/Home';
import Refine from '../pages/Refine';
import Results from '../pages/Results';
import { Mock, describe, it, beforeEach, afterEach, expect, vi } from 'vitest';
import { useMakes } from '../hooks/useMakes';
import { useModels } from '../hooks/useModels';

vi.mock('../hooks/useMakes', () => ({
  useMakes: vi.fn(),
}));
vi.mock('../hooks/useModels', () => ({
  useModels: vi.fn(),
}));

function renderWithRouter(initialEntries: string[] = ['/']) {
  return render(
    <MemoryRouter initialEntries={initialEntries}>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/refine" element={<Refine />} />
        <Route path="/results" element={<Results />} />
      </Routes>
    </MemoryRouter>,
  );
}

describe('Home Page', () => {
  beforeEach(() => {
    (useMakes as Mock).mockReturnValue([
      { makeId: 1, makeName: 'Toyota' },
      { makeId: 2, makeName: 'Honda' },
    ]);

    (useModels as Mock).mockImplementation((makeId: number | null) => {
      if (makeId === 1) return [{ modelId: 1, modelName: 'Corolla' }];
      if (makeId === 2) return [{ modelId: 2, modelName: 'Civic' }];
      return [];
    });

    vi.stubGlobal(
      'fetch',
      vi.fn(() =>
        Promise.resolve({
          ok: true,
          json: () => Promise.resolve([{ id: 1, name: 'Mock Car' }]),
        } as Response),
      ),
    );
  });
  afterEach(() => vi.clearAllMocks());

  it('navigates to Refine page if no model is selected', async () => {
    renderWithRouter(['/']);

    const nextButton = screen.getByRole('button', { name: /next/i });
    await userEvent.click(nextButton);

    expect(
      await screen.findByRole('heading', { level: 2, name: /refine your search/i }),
    ).toBeInTheDocument();
  });

  it('navigates to Results page when make and model are selected', async () => {
    renderWithRouter(['/']);

    await userEvent.selectOptions(screen.getByLabelText(/make/i), 'Toyota');
    await userEvent.selectOptions(screen.getByLabelText(/model/i), 'Corolla');

    await userEvent.click(screen.getByRole('button', { name: /next/i }));

    expect(
      await screen.findByRole('heading', { level: 1, name: /we recommend/i }),
    ).toBeInTheDocument();
  });
});
