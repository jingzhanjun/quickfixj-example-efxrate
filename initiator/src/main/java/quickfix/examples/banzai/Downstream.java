/*******************************************************************************
 * Copyright (c) quickfixengine.org  All rights reserved.
 *
 * This file is part of the QuickFIX FIX Engine
 *
 * This file may be distributed under the terms of the quickfixengine.org
 * license as defined by quickfixengine.org and appearing in the file
 * LICENSE included in the packaging of this file.
 *
 * This file is provided AS IS with NO WARRANTY OF ANY KIND, INCLUDING
 * THE WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE.
 *
 * See http://www.quickfixengine.org/LICENSE for licensing information.
 *
 * Contact ask@quickfixengine.org if any conditions of this licensing
 * are not clear to you.
 ******************************************************************************/

package quickfix.examples.banzai;

import com.pactera.fix.domain.MDR;
import com.pactera.fix.domain.RS;
import org.quickfixj.jmx.JmxExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.*;
import quickfix.field.*;
import quickfix.fix43.NewOrderSingle;
import quickfix.fix50sp1.MarketDataRequest;
import quickfix.fix50sp1.QuoteCancel;
import quickfix.fix50sp1.QuoteRequest;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Entry point for the Downstream application.
 */
public class Downstream {
    private static final CountDownLatch shutdownLatch = new CountDownLatch(1);

    private static final Logger log = LoggerFactory.getLogger(Downstream.class);
    private static Downstream downstream;
    private boolean initiatorStarted = false;
    private static Initiator initiator = null;

    public Downstream(String[] args) throws Exception {
        InputStream inputStream = null;
        if (args.length == 0) {
            inputStream = Downstream.class.getResourceAsStream("banzai.cfg");
        }
        if (inputStream == null) {
            System.out.println("usage: " + Downstream.class.getName() + " [configFile].");
            return;
        }
        SessionSettings settings = new SessionSettings(inputStream);
        inputStream.close();

        boolean logHeartbeats = Boolean.valueOf(System.getProperty("logHeartbeats", "true"));

        OrderTableModel orderTableModel = new OrderTableModel();
        ExecutionTableModel executionTableModel = new ExecutionTableModel();
        BanzaiApplication application = new BanzaiApplication(orderTableModel, executionTableModel);
        MessageStoreFactory messageStoreFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new ScreenLogFactory(true, true, true, logHeartbeats);
        MessageFactory messageFactory = new DefaultMessageFactory();

        initiator = new SocketInitiator(application, messageStoreFactory, settings, logFactory, messageFactory);

        JmxExporter exporter = new JmxExporter();
        exporter.register(initiator);
    }

    public synchronized void logon() {
        if (!initiatorStarted) {
            try {
                initiator.start();
                initiatorStarted = true;
            } catch (Exception e) {
                log.error("Logon failed", e);
            }
        } else {
            for (SessionID sessionId : initiator.getSessions()) {
                Session.lookupSession(sessionId).logon();
            }
        }
    }

    public void logout() {
        for (SessionID sessionId : initiator.getSessions()) {
            Session.lookupSession(sessionId).logout("user requested");
        }
    }

    public void stop() {
        shutdownLatch.countDown();
    }

    public static void main(String[] args) throws Exception {
        try {
            downstream = new Downstream(new String[]{});
            downstream.logon();
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }finally{
            String symbols="AUD.CAD,AUD.CHF,AUD.HKD,AUD.JPY,AUD.NZD,AUD.USD,CAD.CHF,CAD.HKD,CAD.JPY,CHF.HKD,CHF.JPY,EUR.AUD,EUR.CAD,EUR.CHF,EUR.GBP,EUR.HKD,EUR.JPY,EUR.NZD,EUR.USD,GBP.AUD,GBP.CAD,GBP.CHF,GBP.HKD,GBP.JPY,GBP.NZD,GBP.USD,HKD.CNH,HKD.JPY,NZD.CAD,NZD.CHF,NZD.HKD,NZD.JPY,NZD.USD,USD.CAD,USD.CHF,USD.CNH,USD.HKD,USD.JPY,XAU.USD";
            String[] symbolsArray=symbols.split("[,]");
            if(args!=null&&args.length>0){
                String symbol=args[4];
                if(symbol!=null&&!symbol.equals("")){
                    if(symbol.equals("ALL")){
                        for(int i=0;i<39;i++){
                            MDR mdr=new MDR();
                            List<RS> rsGroup=new ArrayList<>();
                            RS rs=new RS();
                            rs.setSymbol(symbolsArray[i]);
                            rs.setMdEntrySize(Double.valueOf(args[0]));
                            rsGroup.add(rs);
                            mdr.setRsGroup(rsGroup);
                            mdr.setPartyID("EFX_PRICE");
                            mdr.setMdReqID(UUID.randomUUID().toString());
                            mdr.setAccount(args[5]);
                            mdr.setSettlType(args[1]);
                            mdr.setSubscriptionRequestType(args[2].charAt(0));
                            mdr.setApplSeqNum(Integer.valueOf(args[3]));
                            testMarketDataRequest(mdr);
                        }
                    }else{
                        MDR mdr=new MDR();
                        List<RS> rsGroup=new ArrayList<>();
                        RS rs=new RS();
                        rs.setSymbol(symbol);
                        rs.setMdEntrySize(Double.valueOf("2500"));
                        rsGroup.add(rs);
                        mdr.setRsGroup(rsGroup);
                        mdr.setPartyID("EFX_PRICE");
                        mdr.setMdReqID(UUID.randomUUID().toString());
                        mdr.setAccount(args[5]);
                        mdr.setSettlType("0");
                        mdr.setSubscriptionRequestType('1');
                        mdr.setApplSeqNum(1);
                        testMarketDataRequest(mdr);
                    }
                }
            }else{
                for(int i=0;i<39;i++){
                    MDR mdr=new MDR();
                    List<RS> rsGroup=new ArrayList<>();
                    RS rs=new RS();
                    rs.setSymbol(symbolsArray[i]);
                    rs.setMdEntrySize(Double.valueOf("2500"));
                    rsGroup.add(rs);
                    mdr.setRsGroup(rsGroup);
                    mdr.setPartyID("EFX_PRICE");
                    mdr.setMdReqID(UUID.randomUUID().toString());
                    mdr.setAccount("EFXTraderPrice");
                    mdr.setSettlType("0");
                    mdr.setSubscriptionRequestType('1');
                    mdr.setApplSeqNum(1);
                    testMarketDataRequest(mdr);
                }
            }
        }
        shutdownLatch.await();
    }

    private static void testMarketDataRequest(MDR mdr) {
        try {
            MarketDataRequest marketDataRequest=new MarketDataRequest();
            MarketDataRequest.NoRelatedSym sGroup=new MarketDataRequest.NoRelatedSym();
            List<RS> rsl=mdr.getRsGroup();
            sGroup.setField(new Symbol(rsl.get(0).getSymbol()));//???????????????
            sGroup.setField(new MDEntrySize(rsl.get(0).getMdEntrySize()));//????????????
            marketDataRequest.addGroup(sGroup);
            marketDataRequest.setField(new SubscriptionRequestType(mdr.getSubscriptionRequestType()));//0-full,1-full+update,2-unsubscribe
            marketDataRequest.setField(new MDReqID(mdr.getMdReqID()));//?????????
            marketDataRequest.setField(new PartyID(mdr.getPartyID()));//EFX-EFX_PRICE,PDP-PDP_PRICE
            marketDataRequest.setField(new ApplSeqNum(mdr.getApplSeqNum()));//?????????????????????????????????????????????1181??????
            marketDataRequest.setField(new Account(mdr.getAccount()));
            marketDataRequest.setField(new SettlType(mdr.getSettlType()));//0-SPOT,1-TODAY
            Session.sendToTarget(marketDataRequest,initiator.getSessions().get(0));
        } catch (SessionNotFound sessionNotFound) {
            sessionNotFound.printStackTrace();
        }
    }

}
