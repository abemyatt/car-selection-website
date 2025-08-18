/// <reference types="vite/client" />

interface ImportMetaEnv {
  readonly VITE_BACKEND_BASE_URL: string;
  readonly VITE_BACKEND_BASE_PATH: string;
}

interface ImportMeta {
  readonly env: ImportMetaEnv;
}
