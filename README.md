# bluetooth-simple-android-plugin

Capacitorjs plugin to use the bluetooth adapter of an android device. simply and direct / Plugin de Capacitorjs para usar el adaptador bluetooth de un dispositivo Android. simple y directo

## Install

```bash
npm install bluetooth-simple-android-plugin
npx cap sync
```

## API

<docgen-index>

* [`setName(...)`](#setname)
* [`startDiscovery(...)`](#startdiscovery)
* [`setDiscoverable()`](#setdiscoverable)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### setName(...)

```typescript
setName(options: { name: string; }) => any
```

Cambia el nombre del dispositivo. Change the name of device.

| Param         | Type                           | Description                                               |
| ------------- | ------------------------------ | --------------------------------------------------------- |
| **`options`** | <code>{ name: string; }</code> | Nombre que queires cambiar. Name that you want to change. |

**Returns:** <code>any</code>

--------------------


### startDiscovery(...)

```typescript
startDiscovery(callback: MyPluginCallback) => void
```

Activa la detección de los dispositivos visibles. Cada vez que se detecta un nuevo 
dispositivo se lanza la funcion callback pasada por parámetros. 
Activate the detection of visible devices. For each new device is detected, 
is throw the callback function passed by params.

| Param          | Type                                                         | Description                                                            |
| -------------- | ------------------------------------------------------------ | ---------------------------------------------------------------------- |
| **`callback`** | <code>(message: MyData \| null, err?: any) =&gt; void</code> | Función que se ejecutará segun se vayan descubriendo los dispositivos. |

--------------------


### setDiscoverable()

```typescript
setDiscoverable() => any
```

Activa el estado de ser detectado del dispositivo. Activate the state of to be detected of device.

**Returns:** <code>any</code>

--------------------

</docgen-api>
