package ua.lipenets.currency_exchange.parser;

public interface AbstractParser<D, M> {
    M toModel(D dto);
}
