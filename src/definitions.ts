export type CallbackID = string;

export interface MyData {
  data: string;
}

export type MyPluginCallback = (message: MyData | null, err?: any) => void;

export interface BluetoothSimplePlugin {
  /**
   * Cambia el nombre del dispositivo. Change the name of device. 
   * @param options Nombre que queires cambiar. Name that you want to change.
   * @return Promise que resuelve con el valor OK o KO. Promise that resolve with
   * the value OK or KO. 
   */
  setName(options: {name: string}): Promise<{ value: string }>;
  /**
   * Activa la detección de los dispositivos visibles. Cada vez que se detecta un nuevo 
   * dispositivo se lanza la funcion callback pasada por parámetros. 
   * Activate the detection of visible devices. For each new device is detected, 
   * is throw the callback function passed by params.
   * @param callback Función que se ejecutará segun se vayan descubriendo los dispositivos. 
   */
  startDiscovery(callback: MyPluginCallback): void;
  /**
   * Activa el estado de ser detectado del dispositivo. Activate the state of to be detected of device.
   * 
   * @return Promise que resuelve con el valor OK o KO. Promise that resolve with
   * the value OK or KO. 
   */
  setDiscoverable(): Promise<{ value: string }>;
}
