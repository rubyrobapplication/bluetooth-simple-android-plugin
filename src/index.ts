import { registerPlugin } from '@capacitor/core';

import type { BluetoothSimplePlugin } from './definitions';

const BluetoothSimple = registerPlugin<BluetoothSimplePlugin>('BluetoothSimple', {
  web: () => import('./web').then(m => new m.BluetoothSimpleWeb()),
});

export * from './definitions';
export { BluetoothSimple };
