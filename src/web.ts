import { WebPlugin } from '@capacitor/core';

import type { BluetoothSimplePlugin, MyPluginCallback } from './definitions';

export class BluetoothSimpleWeb extends WebPlugin implements BluetoothSimplePlugin {

  setName(options: { name: string }): Promise<{ value: string }> {
    console.log(options);
    console.warn('el dispositivo no es android. Se devuelve el valor por defecto.');
    return new Promise((resolve) => { resolve({ value: 'OK' }) });
  };

  startDiscovery(callback: MyPluginCallback): void {
    console.log(callback)
    console.warn('el dispositivo no es android. Esta función deber ser mockeada por el desarrollador para usarse en web');
  };

  setDiscoverable(): Promise<{ value: string }>{
    console.warn('el dispositivo no es android. Se devuelve el valor por defecto.');
    return new Promise((resolve) => { resolve({ value: 'OK' }) });
  };
}
