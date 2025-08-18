import React, { useMemo, useState } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import Dropdown from '../components/Dropdown/Dropdown';
import { useMakes } from '../hooks/useMakes';
import { useModels } from '../hooks/useModels';

export default function Home() {
  const navigate = useNavigate();
  const [search] = useSearchParams();

  const [selectedMakeName, setSelectedMakeName] = useState(search.get('make') ?? '');
  const [selectedModelName, setSelectedModelName] = useState(search.get('model') ?? '');

  const makes = useMakes();

  const makeId = useMemo(() => {
    const make = makes.find((m) => m.makeName === selectedMakeName);
    return make ? make.makeId : null;
  }, [selectedMakeName, makes]);

  const models = useModels(makeId);

  const makeOptions = useMemo(
    () => [
      { label: 'Select make', value: '' },
      ...makes.map((m) => ({ label: m.makeName, value: m.makeName })),
    ],
    [makes],
  );

  const modelOptions = useMemo(
    () => [
      { label: 'Select model', value: '' },
      ...models.map((m) => ({ label: m.modelName, value: m.modelName })),
    ],
    [models],
  );

  const onNext = () => {
    if (selectedMakeName && selectedModelName) {
      navigate({
        pathname: '/results',
        search: new URLSearchParams({
          make: selectedMakeName,
          model: selectedModelName,
        }).toString(),
      });
    } else {
      const params = new URLSearchParams();
      if (selectedMakeName) params.set('make', selectedMakeName);
      navigate({ pathname: '/refine', search: params.toString() });
    }
  };

  return (
    <section>
      <h2>Do you know what make and model of car you want?</h2>
      <Dropdown
        label="Make"
        aria-label="make"
        value={selectedMakeName}
        onChange={(e) => {
          setSelectedMakeName(e.target.value);
          setSelectedModelName('');
        }}
        options={makeOptions}
      />

      <Dropdown
        label="Model"
        aria-label="model"
        value={selectedModelName}
        onChange={(e) => setSelectedModelName(e.target.value)}
        options={modelOptions}
        disabled={!selectedMakeName}
      />

      <button onClick={onNext}>Next</button>
    </section>
  );
}
