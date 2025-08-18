import React from 'react';
import { render, screen } from '@testing-library/react';
import { MemoryRouter, Route, Routes } from 'react-router-dom';
import { Mock, describe, it, vi, beforeEach, expect } from 'vitest';
import Results from '../pages/Results';
import { useCars } from '../hooks/useCars';

vi.mock('../hooks/useCars', () => ({
  useCars: vi.fn(),
}));

function renderWithRouter(initialEntries: string[] = ['/results']) {
  return render(
    <MemoryRouter initialEntries={initialEntries}>
      <Routes>
        <Route path="/results" element={<Results />} />
      </Routes>
    </MemoryRouter>,
  );
}

describe('Results Page', () => {
  beforeEach(() => {
    vi.clearAllMocks();
  });

  it('shows loading state', () => {
    (useCars as unknown as Mock).mockReturnValue({
      cars: [],
      loading: true,
      error: null,
    });

    renderWithRouter(['/results']);

    expect(screen.getByText(/loading/i)).toBeInTheDocument();
  });

  it('shows error state', () => {
    (useCars as unknown as Mock).mockReturnValue({
      cars: [],
      loading: false,
      error: 'Something went wrong',
    });

    renderWithRouter(['/results']);

    expect(screen.getByText(/something went wrong while fetching cars./i)).toBeInTheDocument();
  });

  it('shows "no cars found" when cars is empty', () => {
    (useCars as unknown as Mock).mockReturnValue({
      cars: [],
      loading: false,
      error: null,
    });

    renderWithRouter(['/results']);

    expect(screen.getByText(/no cars found/i)).toBeInTheDocument();
  });

  it('renders car list when cars are returned', () => {
    (useCars as unknown as Mock).mockReturnValue({
      cars: [
        { make: 'Toyota', model: 'Corolla' },
        { make: 'Honda', model: 'Civic' },
      ],
      loading: false,
      error: null,
    });

    renderWithRouter(['/results?fuelType=Diesel']);

    expect(screen.getByRole('heading', { name: /we recommend/i })).toBeInTheDocument();

    expect(screen.getByText(/Toyota Corolla/)).toBeInTheDocument();
    expect(screen.getByText(/Honda Civic/)).toBeInTheDocument();
  });
});
