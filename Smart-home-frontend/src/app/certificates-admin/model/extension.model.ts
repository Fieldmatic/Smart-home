import { Capabilities } from './capabilities.model';

export class Extension {
  constructor(
    public id: string,
    public name: string,
    public capabilities: Capabilities[]
  ) {}
}
