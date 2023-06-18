import { Extension } from './extension.model';

export class Capabilities {
  constructor(
    public id: string,
    public name: string,
    public extension: Extension
  ) {}
}
