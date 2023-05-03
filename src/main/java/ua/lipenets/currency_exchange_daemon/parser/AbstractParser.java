package ua.lipenets.currency_exchange_daemon.parser;

public interface AbstractParser<D, M> {
    M toModel(D dto);
}
