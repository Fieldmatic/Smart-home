export class Certificate {
  constructor(
    public email: string,
    public issuedFor: string,
    public issuedBy: string,
    public start: Date,
    public end: Date,
    public valid: boolean
  ) {}
}
