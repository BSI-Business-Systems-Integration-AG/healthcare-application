package org.eclipse.scout.healthcare.server.common;

public interface ResetSQLs {

  String TRUNCATE_DEALS = "truncate deal";

  String SETUP_NETWORK_ALPHABET_DEAL_1 = ""
      + " insert into deal ("
      + "   deal_id, organization_id, quantity, exchange_rate, order_book_type, trading_action, status"
      + " ) values ("
      + "   10, '064eec1f-d178-4822-9928-b6121e7a0943', 2300000, 1.02, 'USDEUR', 'SELL', '7edf08d0-1423-400f-8e20-b022694aa0ea'"
      + " ) ";

  String SETUP_NETWORK_ALPHABET_DEAL_2 = ""
      + " insert into deal ("
      + "   deal_id, organization_id, quantity, exchange_rate, order_book_type, trading_action, status"
      + " ) values ("
      + "   20, '064eec1f-d178-4822-9928-b6121e7a0943', 1250000, 0.88, 'USDEUR', 'BUY', '7edf08d0-1423-400f-8e20-b022694aa0ea'"
      + " ); ";

  String SETUP_NETWORK_BSI_DEAL_1 = ""
      + " insert into deal ("
      + "   deal_id, organization_id, quantity, exchange_rate, order_book_type, trading_action, status"
      + " ) values ("
      + "   30, '29509134-8031-4ff8-9900-a670c87126cc', 1750000, 0.98, 'USDEUR', 'SELL', '7edf08d0-1423-400f-8e20-b022694aa0ea'"
      + " ); ";

  String SETUP_NETWORK_BSI_DEAL_2 = ""
      + " insert into deal ("
      + "   deal_id, organization_id, quantity, exchange_rate, order_book_type, trading_action, status"
      + " ) values ("
      + "   40, '29509134-8031-4ff8-9900-a670c87126cc', 1100000, 0.93, 'USDEUR', 'BUY', '7edf08d0-1423-400f-8e20-b022694aa0ea'"
      + " ); ";

  String SETUP_NETWORK_LINDE_DEAL_1 = ""
      + " insert into deal ("
      + "   deal_id, organization_id, quantity, exchange_rate, order_book_type, trading_action, status"
      + " ) values ("
      + "   50, '8651a052-8752-4f6f-9498-80df7e49115d', 1200000, 0.97, 'USDEUR', 'SELL', '7edf08d0-1423-400f-8e20-b022694aa0ea'"
      + " ) ";

  String SETUP_NETWORK_LINDE_DEAL_2 = ""
      + " insert into deal ("
      + "   deal_id, organization_id, quantity, exchange_rate, order_book_type, trading_action, status"
      + " ) values ("
      + "   60, '8651a052-8752-4f6f-9498-80df7e49115d', 500000, 0.91, 'USDEUR', 'BUY', '7edf08d0-1423-400f-8e20-b022694aa0ea'"
      + " ) ";

  String SETUP_NETWORK_FLAUCHER_DEAL_1 = ""
      + " insert into deal ("
      + "   deal_id, organization_id, quantity, exchange_rate, order_book_type, trading_action, status"
      + " ) values ("
      + "   70, 'db4bc11f-af29-4092-a33c-5a9556e775f8', 2000000, 0.95, 'USDEUR', 'SELL', '7edf08d0-1423-400f-8e20-b022694aa0ea'"
      + " ) ";

  String SETUP_OTHER_ALPHABET_DEAL_1 = ""
      + " insert into deal ("
      + "   deal_id, organization_id, quantity, exchange_rate, order_book_type, trading_action, status"
      + " ) values ("
      + "   80, '064eec1f-d178-4822-9928-b6121e7a0943', 800000, 1.12, 'GBPEUR', 'BUY', '7edf08d0-1423-400f-8e20-b022694aa0ea'"
      + " ) ";

  String SETUP_OTHER_ALPHABET_DEAL_2 = ""
      + " insert into deal ("
      + "   deal_id, organization_id, quantity, exchange_rate, order_book_type, trading_action, status"
      + " ) values ("
      + "   90, '064eec1f-d178-4822-9928-b6121e7a0943', 20000, 121.7, 'EURJPY', 'BUY', '7edf08d0-1423-400f-8e20-b022694aa0ea'"
      + " ) ";

}
