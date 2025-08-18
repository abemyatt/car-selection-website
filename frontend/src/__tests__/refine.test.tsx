import React from 'react';
import * as ReactRouterDom from 'react-router-dom';
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { MemoryRouter, Route, Routes } from 'react-router-dom';
import { describe, it, expect, vi } from 'vitest';
import Refine from '../pages/Refine';
import Layout from '../components/Layout/Layout';

const mockNavigate = vi.fn();

vi.mock('react-router-dom', async () => {
  const actual: typeof ReactRouterDom = await vi.importActual('react-router-dom');
  return {
    ...actual,
    useNavigate: () => mockNavigate,
  };
});

describe('Refine Page', () => {
  const renderWithRouter = () =>
    render(
      <MemoryRouter initialEntries={['/refine']}>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route path="refine" element={<Refine />} />
            <Route path="results" element={<h1>We recommend any of the following cars</h1>} />
          </Route>
        </Routes>
      </MemoryRouter>,
    );

  it('renders all dropdowns and submit button', () => {
    renderWithRouter();

    expect(screen.getByLabelText(/number of doors/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/body type/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/transmission/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/fuel type/i)).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /get results/i })).toBeInTheDocument();
  });

  it('updates dropdown values on selection', async () => {
    renderWithRouter();

    const doors = screen.getByLabelText(/number of doors/i) as HTMLSelectElement;
    const bodyType = screen.getByLabelText(/body type/i) as HTMLSelectElement;
    const transmission = screen.getByLabelText(/transmission/i) as HTMLSelectElement;
    const fuelType = screen.getByLabelText(/fuel type/i) as HTMLSelectElement;

    await userEvent.selectOptions(doors, '3');
    await userEvent.selectOptions(bodyType, 'SUV');
    await userEvent.selectOptions(transmission, 'Automatic');
    await userEvent.selectOptions(fuelType, 'Diesel');

    expect(doors.value).toBe('3');
    expect(bodyType.value).toBe('SUV');
    expect(transmission.value).toBe('Automatic');
    expect(fuelType.value).toBe('Diesel');
  });

  it('ignores "I don\'t mind" selections in query params', async () => {
    renderWithRouter();

    await userEvent.selectOptions(screen.getByLabelText(/number of doors/i), 'any');
    await userEvent.selectOptions(screen.getByLabelText(/body type/i), 'any');
    await userEvent.selectOptions(screen.getByLabelText(/transmission/i), 'Automatic');
    await userEvent.selectOptions(screen.getByLabelText(/fuel type/i), 'Diesel');

    // Unsure why we are getting multiple buttons
    const submitButton = screen.getAllByRole('button', { name: /get results/i });
    await userEvent.click(submitButton[0]);

    expect(mockNavigate).toHaveBeenCalledWith({
      pathname: '/results',
      search: 'transmission=Automatic&fuelType=Diesel',
    });
  });
});
