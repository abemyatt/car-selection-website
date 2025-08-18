import { useEffect, useState } from 'react';
import { fetchModelsByMake } from '../services/api';
import { ModelDTO } from '../types/ModelDTO';

export function useModels(makeId: number | null) {
  const [models, setModels] = useState<ModelDTO[]>([]);

  useEffect(() => {
    if (!makeId) {
      setModels([]);
      return;
    }
    fetchModelsByMake(makeId).then(setModels).catch(console.error);
  }, [makeId]);

  return models;
}
